job("Job4"){
    description("first job")
    triggers {
            upstream('Job3', 'SUCCESS')
            cron('* * * * *')
    }
    steps {
        shell ('''
if [ $(curl -s -o /dev/null -w "%{http_code}" 192.168.99.101:31000/index.html) == '200' ]
then
	echo "Webpage tested successfully"
    exit 0
else
	echo "Error code $statuscode"
    echo "triggering the job2 for creating resources for the deployment and mailing to the dev"
    sudo python3  /storage/mail_script.py
    curl --user "admin:<password>" http://192.168.99.102:8080/job/Job1/build?token=secret
    exit 0
fi
        ''')
    }
}