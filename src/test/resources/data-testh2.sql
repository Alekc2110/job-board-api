INSERT INTO job_entity (id, company_name, location, slug, title, url, description, remote, created_at) VALUES
(1, 'Company1', 'Location1', 'slug description', 'title1', 'https://www.arbeitnow.com/jobs/companies/desc-314877', 'descr1', true, 21155766),
(2, 'Company2', 'Location2', 'slug description2', 'title2', 'https://www.arbeitnow.com/jobs/companies/desc-314888', 'descr2', false, 211557555),
(3, 'Company3', 'Location3', 'slug description3', 'title3', 'https://www.arbeitnow.com/jobs/companies/desc-314899', 'descr3', true, 211552252),
(4, 'Company4', 'Location4', 'slug description4', 'title4', 'https://www.arbeitnow.com/jobs/companies/desc-314800', 'descr4', false, 72744422),
(5, 'Company5', 'Location5', 'slug description5', 'title5', 'https://www.arbeitnow.com/jobs/companies/desc-314801', 'descr5', true, 22424241);

INSERT INTO job_types (job_entity_id, job_types) VALUES
(1, 'type1'),
(1, 'type2'),
(1, 'type3'),
(1, 'type4'),
(1, 'type5'),
(2, 'type1'),
(2, 'type2'),
(2, 'type3'),
(5, 'type1'),
(5, 'type2'),
(5, 'type3');

INSERT INTO tags (job_entity_id, tags) VALUES
(1, 'tug1'),
(1, 'tug2'),
(1, 'tug3'),
(1, 'tug4'),
(1, 'tug5'),
(2, 'tug1'),
(2, 'tug2'),
(2, 'tug3'),
(5, 'tug1'),
(5, 'tug2'),
(5, 'tug3');