pipeline
{
    	agent any
    	stages
	{
            stage('BUILD')
		{
			
            	steps{
                	echo 'Build start...'
			script {
                    		try {	throw exc
					//build command
						//bat '"C:/Program Files/IBM/IIB/10.0.0.12/tools/mqsicreatebar" -data C:/IIB_Workspace -b C:/IIB_Workspace/SHARED_LIB_BBTG_WMB_ESQL_UTILITIES/Demo_jenkin1.bar -l SHARED_LIB_BBTG_WMB_ESQL_UTILITIES -deployAsSource'
					//Artifactory
						//bat  'copy C:\\IIB_Workspace\\SHARED_LIB_BBTG_WMB_ESQL_UTILITIES\\Demo_jenkin1.bar C:\\ARTIFACTORY\\Demo_jenkin1.bar'
				}
				catch(exc){
					//Mailing
						mail bcc: '', body: "Hi \n \n *This was an auto generated mail from Jenkins please do not reply* \n \n --------------------------------------------------------------------------------------- \n see <${env.BUILD_URL}console> \n --------------------------------------------------------------------------------------- \n Flow Name: ${env.JOB_NAME}, Build Number : ${env.BUILD_NUMBER} and Branch Name: ${env.BRANCH_NAME} \n \n Regards \n Jenkins/Broker", cc: '', from: 'jenkins@gmail.com', replyTo: '', subject: "Build failed in Jenkins:${env.JOB_NAME}#${env.BUILD_NUMBER}", to: 'manojsen115@gmail.com'
				
				}
			}
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
                	//bat '"C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsiprofile" && "C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsideploy" TEST_NODE -e default -a C:\\ARTIFACTORY\\Demo_jenkin1.bar'
                	echo 'Deployee finish...
			}
        	}
	
    }
    post
	{ 
        always 
	    { 
            	echo 'Status:'
       	    }
    	}
}

