job("Job4"){
    description("first job")

    scm {
        github('A4ANK/jenkins_jobDSL1.git', 'master')
    }
    triggers {
            upstream('Job3', 'SUCCESS')
    }
    steps {
        shell ('
            
        ')
    }
}