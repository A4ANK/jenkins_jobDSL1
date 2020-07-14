job("Job3"){
    description("first job")

    scm {
        github('A4ANK/jenkins_jobDSL1.git', 'master')
    }
    triggers {
            upstream('Job1', 'SUCCESS')
    }
    steps {
        shell ('
            
        ')
    }
}