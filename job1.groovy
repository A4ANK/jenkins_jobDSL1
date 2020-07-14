job("Job1"){
    description("first job")
    authenticationToken('secret')

    scm {
        github('A4ANK/jenkins_jobDSL1', 'master')
    }
    triggers {
        gitHubPushTrigger()   
    }
    steps {
        shell ('''
sudo cp -rf * /storage/
if sudo kubectl get pvc httpd-pvc
then
	echo "PVC & PV already created, applying latest changes and copying the webpage"
    sudo kubectl apply -f /storage/httpd-pvc.yml
    x=$(sudo find /storage -name '*httpd-pvc-pvc*')
    sudo  cp -rf /storage/index.html  $x
else
	sudo kubectl apply -f /storage/httpd-pvc.yml
    sudo sleep 5
    x=$(sudo find /storage -name '*httpd-pvc-pvc*')
    sudo  cp -rf /storage/index.html  $x
    echo "Creating pvc and copying the webpage"
fi 


if sudo kubectl get svc httpd-service
then
	echo "service already created and applying latest changes"
    sudo kubectl apply -f /storage/httpd-svc.yml
else
	sudo kubectl apply -f /storage/httpd-svc.yml
    echo "Creating service"
fi 
	
if sudo kubectl get deploy httpd-webapp
then
	echo "Deployment already created and applying latest changes"
    sudo kubectl apply -f /storage/httpd-deployment.yml
else
	sudo kubectl apply -f /storage/httpd-deployment.yml
    echo "Creating Deployment"
fi
        ''')
    }
}