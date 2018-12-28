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
			//bat '"C:/Program Files/IBM/IIB/10.0.0.12/tools/mqsicreatebar" -data C:/Users/A1408426/IBM/1.2_JDA_TESTING -b C:/Demo_jenkin1.bar -l SHARED_LIB_BBTG_WMB_ESQL_UTILITIES -deployAsSource'
                	//bat  'copy C:\\KUNAL\\DEMOBAR.BAR C:\\ARTIFACTORY\\ABC.BAR'
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
                	echo 'Deployee finish...'
            		bat '"C:/Program Files/IBM/IIB/10.0.0.12/server/bin/mqsideploy" TESTNODE_A1408426 -e default -a C:\\ARTIFACTORY\\ABC.BAR'
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
