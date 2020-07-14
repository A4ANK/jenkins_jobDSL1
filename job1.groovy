job("Job1"){
    description("First job")
    authenticationToken('secret')
    label('dynamic')
    scm {
        github('A4ANK/jenkins_jobDSL1', 'master')
    }
    triggers {
        gitHubPushTrigger()   
    }
    steps {
        shell ('''
 cp -rf * /storage/
if  kubectl get pvc httpd-pvc
then
	echo "PVC & PV already created, applying latest changes and copying the webpage"
     kubectl apply -f /storage/httpd-pvc.yml
    x=$( find /storage -name '*httpd-pvc-pvc*')
      cp -rf /storage/index.html  $x
else
	 kubectl apply -f /storage/httpd-pvc.yml
     sleep 5
    x=$( find /storage -name '*httpd-pvc-pvc*')
      cp -rf /storage/index.html  $x
    echo "Creating pvc and copying the webpage"
fi 


if  kubectl get svc httpd-service
then
	echo "service already created and applying latest changes"
     kubectl apply -f /storage/httpd-svc.yml
else
	 kubectl apply -f /storage/httpd-svc.yml
    echo "Creating service"
fi 
	
if  kubectl get deploy httpd-webapp
then
	echo "Deployment already created and applying latest changes"
     kubectl apply -f /storage/httpd-deployment.yml
else
	 kubectl apply -f /storage/httpd-deployment.yml
    echo "Creating Deployment"
fi
        ''')
    }
}

buildPipelineView('project-A') {
    title('Project A CI Pipeline')
    displayedBuilds(5)
    selectedJob('Job1')
    showPipelineParameters()
    refreshFrequency(60)
}