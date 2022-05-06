/*
package com.rajat.springbatch.config;

public class CsvFileToDatabaseConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    // begin reader, writer, and processor


    @Bean
    public FlatFileItemReader<AnimeDTO> csvAnimeReader(){
        FlatFileItemReader<AnimeDTO> reader = new FlatFileItemReader<AnimeDTO>();
        reader.setResource(new ClassPathResource("animescsv.csv"));
        reader.setLineMapper(new DefaultLineMapper<AnimeDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "id", "title", "description" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<AnimeDTO>() {{
                setTargetType(AnimeDTO.class);
            }});
        }});
        return reader;
    }


    @Bean
    ItemProcessor<AnimeDTO, AnimeDTO> csvAnimeProcessor() {
        return new AnimeProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<AnimeDTO> csvAnimeWriter() {
        JdbcBatchItemWriter<AnimeDTO> csvAnimeWriter = new JdbcBatchItemWriter<AnimeDTO>();
        csvAnimeWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<AnimeDTO>());
        csvAnimeWriter.setSql("INSERT INTO animes (id, title, description) VALUES (:id, :title, :description)");
        csvAnimeWriter.setDataSource(dataSource);
        return csvAnimeWriter;
    }

    // end reader, writer, and processor

    // begin job info
    @Bean
    public Step csvFileToDatabaseStep() {
        return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<AnimeDTO, AnimeDTO>chunk(1)
                .reader(csvAnimeReader())
                .processor(csvAnimeProcessor())
                .writer(csvAnimeWriter())
                .build();
    }

    @Bean
    Job csvFileToDatabaseJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(csvFileToDatabaseStep())
                .end()
                .build();
    }
}
*/
