pipeline
{
    	agent any
    	stages
	{
        	//stage ('Build1')
		//{
		  // Run the gradle build for release
			//sh "git config --global user.email \"jenkins@bestbuy.com\""
			//sh "git config --global user.name \"jenkins\""
			//sh "git tag -a $version -m \"[ release-build : $version ]\""
			//sh "git push origin $version"
			//rtGradle.run buildFile: 'build.gradle', tasks: 'clean build sonarqube dependencyCheck', switches: '--stacktrace'
			//junit 'build/test-results/test/*.xml'
			//step([$class: 'DependencyCheckPublisher'])  
			//sh returnStdout: true, script: "sed -r 's/(version=)([0-9]+)(.)([0-9]+)(.)([0-9]+)/echo \\1\\2\\3\\4\\5\$\\((\\6+1))\\-SNAPSHOT/ge' -i gradle.properties"
			//sh "git add gradle.properties && git commit -m \"[ release-build-complete ] : incrementing development snapshot version\" && git push origin master"
		//}
    		//stage ('Deploy Image to CI Test')
		//{
		  // Deploying new container image
		 // sh "/opt/apps/scripts/deployment/deployservice.py ${OPENSHIFT_TENANT_PROJECT_NAME}-test $service_name $version $region 1 ci 2181 port-forward"
		//}
            stage('BUILD')
		{
			when // we can use if condition here
			{
			expression
				{
				// "expression" can be any Groovy expression
				echo false==[[ "SNAPSHOT" =~ "SNAPSHOT" ]]+''
				return false==[[ "SNAPSHOT" =~ "SNAPSHOT" ]] 
				}
			}
            		steps
			{
                	echo 'Build start...'
                	sleep 1
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
                	input('Do you want to proceed')
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
