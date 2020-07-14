job("Job1"){
    description("first job")

    scm {
        github('A4ANK/jenkins_jobDSL1.git', 'master')
    }
    triggers {
        gitHubPushTrigger()
    }
    steps {
        shell ('
            
        ')
    }
}