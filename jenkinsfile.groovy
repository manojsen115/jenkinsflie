pipeline
{
    	agent any
    	stages
	{
        	stage ('initail')
		{
			def service_name="pipeline_name"
		 def bitbucket_tenant_project_name="ProjectName"
		  def openshift_tenant_project_name=""
      
		
		 def git_url = "https://github.com/manojsen115/ProjectName.git"
     		 git url: git_url, credentialsId: "jenkins", branch: "master"

      		def rtGradle = Artifactory.newGradleBuild()

      		rtGradle.tool = "Gradle"
     		rtGradle.deployer repo: 'libs-release-local', server: server
      		rtGradle.deployer.artifactDeploymentPatterns.addInclude("**/*.war")

      		// Integration Repository validation check
      		sh "/opt/apps/scripts/deployment/check_repository.sh $service_name $service_code"
      	
      		// Run the gradle build for snapshot
       		rtGradle.run buildFile: 'build.gradle' 
			
	}
            stage('BUILD')
		{
			when // we can use if condition here
			{
			expression
				{
				// "expression" can be any Groovy expression
				echo Boolean.toString(false==[[ "SNAPSHOT" =~ "SNAPSHOT" ]])
				return true==[[ "SNAPSHOT" =~ "SNAPSHOT" ]] 
				}
			}
            		steps
			{
                	echo 'Build start...'
                	sleep 1
			rtGradle.run buildFile: 'build.gradle' 
                	cleanWs()
                	input message: 'Do you want to procreed?', ok: 'YES'
                       	gradle
				{     
                              	buildFile('build.gradle')
                              	switches('--stacktrace')
                              	tasks("clean build")
                              	gradleName('Gradle')
                              	useWrapper(false)
                              	}
                	echo 'Build finish...'
            		}
        	}
       	    stage('TEARDOWN')
		{
            	steps
			{
                	echo 'Teardown start...'
                	sleep 1
                	echo 'Teardown finish...'
            		}
        	}
            stage('TEST')
		{
            	steps
			{
                	//input('Do you want to proceed')
               		echo 'Test start...'
                	sleep 1
                	echo 'Test finish...'
            		}
        	}
           stage('Promote')
		{
            	steps
			{
                	echo 'Promote start...'
                	sleep 1
                	echo 'Promote finish...'
            		}
        	}
           stage('DEPLOYEE')
		{
            	steps
			{
                	echo 'Deployee start...'
                	sleep 1
                	echo 'Deployee finish...'
            		//if (true)//("$version".endsWith('-SNAPSHOT'))
	 		//	{
         		//		stage ('Finished')
         		//		echo "Finished the build process"
        		//	} 
        		//else
			//	{
            		//	stage ('Finished')
			//		{
             		//		// Run veracode static scan
            		//		echo "Finished the build process but running a Veracode scan since this is a release build"
            		//		//veracode applicationName: "${VERACODE_APPLICATION_NAME}", createProfile: true, createSandbox: true, criticality: 'Medium', autoscan: true, fileNamePattern: '', pHost: 'usproxy.na.bestbuy.com', pPassword: '', pPort: 8080, pUser: '', replacementPattern: '', sandboxName: "${JOB_NAME}", scanExcludesPattern: '', scanIncludesPattern: '', scanName: "${JOB_NAME}-$version", uploadExcludesPattern: '', uploadIncludesPattern: '**/**.war', useIDkey: true, vid: "${VERACODE_ID}", vkey: "${VERACODE_KEY}", vpassword: '', vuser: ''
         		//		} 
			//	}
			}
        	}
	
    }
    post
	{ 
        always 
	    { 
            	echo 'Status:Success'
       	    }
    	}
}
