job("Job3"){
    description("Third job")
    triggers {
            upstream('Job1', 'SUCCESS')
    }
    steps {
        shell ('''
statuscode=$(curl -s -o /dev/null -w "%{http_code}" 192.168.99.101:31000/index.html)
if [ $statuscode == '200' ]
then
	echo "Webpage tested successfully"
    exit 0
else
	echo "Error code $statuscode"
    exit 1
fi
        ''')
    }
}