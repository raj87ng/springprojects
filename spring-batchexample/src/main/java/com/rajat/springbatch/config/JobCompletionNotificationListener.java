/*
package com.rajat.springbatch.config;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("============ JOB FINISHED ============ Verifying the results....\n");

            List<AnimeDTO> results = jdbcTemplate.query("SELECT id, title, description FROM animes", new RowMapper<AnimeDTO>() {
                @Override
                public AnimeDTO mapRow(ResultSet rs, int row) throws SQLException {
                    return new AnimeDTO(rs.getString(1), rs.getString(2), rs.getString(3));
                }
            });

            for (AnimeDTO AnimeDTO : results) {
                log.info("Discovered <" + AnimeDTO + "> in the database.");
            }

        }
    }
}
*/
