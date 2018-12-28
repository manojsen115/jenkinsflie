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
                	sleep 1
			//build command
			//bat '"C:/Program Files/IBM/IIB/10.0.0.12/tools/mqsicreatebar" -data C:/IIB_Workspace -b C:/IIB_Workspace/RouteToLabel/RouteToLabel.bar -p RouteToLabel -o RouteToLabel\\RouteToLable.msgflow'
                	//bat '"C:/Program Files/IBM/IIB/10.0.0.12/tools/mqsicreatebar" -data C:/IIB_Workspace -b C:/IIB_Workspace/HttpInputNode/HttpInputNode.bar -p HttpInputNode -o HttpInputNode\\HttpInputNode.msgflow'
                	//for shared library
				bat '"C:/Program Files/IBM/IIB/10.0.0.12/tools/mqsicreatebar" -data C:/Users/A1408426/IBM/1.2_JDA_TESTING -b C:/Demo_jenkin1.bar -l SHARED_LIB_BBTG_WMB_ESQL_UTILITIES -deployAsSource
			// bat  'copy C:\\IIB_Workspace\\HttpInputNode\\HttpInputNode.bar C:\\ARTIFACTORY\\HttpInputNode.bar'
			bat  'copy C:\\IIB_Workspace\\SHARED_LIB_BBTG_WMB_ESQL_UTILITIES\\Demo_jenkin1.bar C:\\ARTIFACTORY\\Demo_jenkin1.bar'
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
				bat '"C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsiprofile" && "C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsideploy" TEST_NODE -e default -a C:\\ARTIFACTORY\\Demo_jenkin1.ba'
				//bat '"C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsiprofile" && "C:\\Program Files\\IBM\\IIB\\10.0.0.12\\server\\bin\\mqsideploy" TEST_NODE -e default -a C:\\ARTIFACTORY\\ABC.BAR'
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

