pipeline
{
    	agent any
    	stages
	{
            stage('BUILD')
		{
			
            		steps
			{
                	echo 'Build start...'
				//env.interface_name="${interface_name}"
	               	sleep 1
			script {
                    		try {
			//build command
					//bat '"C:/Program Files/IBM/IIB/10.0.0.12/tools/mqsicreatebar" -data C:/IIB_Workspace -b C:/IIB_Workspace/RouteToLabel/RouteToLabel.bar -p RouteToLabel -o RouteToLabel\\RouteToLable.msgflow'
                			//bat '"C:/Program Files/IBM/IIB/10.0.0.12/tools/mqsicreatebar" -data C:/IIB_Workspace -b C:/IIB_Workspace/HttpInputNode/HttpInputNode.bar -p HttpInputNode -o HttpInputNode\\HttpInputNode.msgflow'
                	//for shared library
		bat '"C:/Program Files/IBM/IIB/10.0.0.12/tools/mqsicreatebar" -data C:/IIB_Workspace -b C:/IIB_Workspace/SHARED_LIB_BBTG_WMB_ESQL_UTILITIES/Demo_jenkin1.bar -l SHARED_LIB_BBTG_WMB_ESQL_UTILITIES -deployAsSource'
					// bat  'copy C:\\IIB_Workspace\\HttpInputNode\\HttpInputNode.bar C:\\ARTIFACTORY\\HttpInputNode.bar'
		bat  'copy C:\\IIB_Workspace\\SHARED_LIB_BBTG_WMB_ESQL_UTILITIES\\Demo_jenkin1.bar C:\\ARTIFACTORY\\Demo_jenkin1.bar'
					//mail bcc: '', body: 'hi this is body', cc: '', from: '', replyTo: '', subject: 'this is jenkins mail', to: 'manojsen115@gmail.com'
//mail bcc: '', body: "this is body ${env.BUILD_URL}", cc: '', from: 'jenkins@gmail.com', replyTo: '', subject: 'FW: Build failed in Jenkins:', to: 'manojsen115@gmail.com' 	
				}
				catch(exc){
				//start
mail bcc: '', body: "Hi \n \n *This was an auto generated mail from Jenkins please do not reply* \n \n --------------------------------------------------------------------------------------- \n see <${env.BUILD_URL}console> \n --------------------------------------------------------------------------------------- \n Flow Name: ${env.JOB_NAME}, Build Number : ${env.BUILD_NUMBER} and Branch Name: ${env.BRANCH_NAME} \n \n Regards \n Jenkins/Broker", cc: '', from: 'jenkins@gmail.com', replyTo: '', subject: "Build failed in Jenkins:${env.JOB_NAME}#${env.BUILD_NUMBER}", to: 'manojsen115@gmail.com'
				
				}}
				
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
                	echo 'Deployee finish...'//bat '"C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsilist"'
					//bat '"C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsiprofile" && "C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsideploy" TEST_NODE -e default -a C:\\ARTIFACTORY\\HttpInputNode.bar'
			// for shared library
				
				
			script {
                    		try {
					//-vidhi	bat '"C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsiprofile" && "C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsideploy" TESTNODE_A1159525 -e default -a C:\\ARTIFACTORY\\Demo_jenkin1.bar'
		bat '"C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsiprofile" && "C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsideploy" TEST_NODE -e default -a C:\\ARTIFACTORY\\Demo_jenkin1.bar'
					//bat '"C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsiprofile" && "C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsideploy" TEST_NODE -e default -a C:\\ARTIFACTORY\\ABC.BAR'
			
				}
				catch(exc){
				//start
mail bcc: '', body: "Hi \n \n *This was an auto generated mail from Jenkins please do not reply* \n \n --------------------------------------------------------------------------------------- \n see <${env.BUILD_URL}console> \n --------------------------------------------------------------------------------------- \n Flow Name: ${env.JOB_NAME}, Build Number : ${env.BUILD_NUMBER} and Branch Name: ${env.BRANCH_NAME} \n \n Regards \n Jenkins/Broker", cc: '', from: 'jenkins@gmail.com', replyTo: '', subject: "Deployement failed in Jenkins:${env.JOB_NAME}#${env.BUILD_NUMBER}", to: 'manojsen115@gmail.com'
				
				}}
			
			
			
			
			
			
			
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
