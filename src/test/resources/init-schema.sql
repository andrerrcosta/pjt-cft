insert into company_position(id, name, salary, bonus, benefits)
    values
    (default, 'secretário', 7000.00, 1000.00, 20.0),
    (default, 'vendedor', 12000.00, 1800.00, 30.0),
    (default, 'gerente', 20000.00, 3000.00, 0.0);

insert into employee (id, name, position_id, hiring_date)
    values
    (default, 'Jorge Carvalho', cast(1 as binary), '2018-01-01'),
    (default, 'Maria Souzaa', 1, '2015-12-01'),
    (default, 'Ana Silva', 2, '2021-12-01'),
    (default, 'João Mendes', 2, '2021-12-01'),
    (default, 'Juliana Alves', 3, '2017-07-01'),
    (default, 'Bento Albino', 3, '2014-03-01');

insert into selling (id, amount, selling_date, employee_id)
    values
    (default, 5200.00, '2021-12-01', 3),
    (default, 4000.00, '2022-01-01', 3),
    (default, 4200.00, '2022-02-01', 3),
    (default, 5850.00, '2022-03-01', 3),
    (default, 7000.00, '2022-04-01', 3),
    (default, 3400.00, '2021-12-01', 4),
    (default, 7700.00, '2022-01-01', 4),
    (default, 5000.00, '2022-02-01', 4),
    (default, 5900.00, '2022-03-01', 4),
    (default, 6500.00, '2022-04-01', 4);

-- PROCEDURES

--CREATE TYPE empMonth AS TABLE
--(id BIGINT(255), hiring_date timestamp);
--
--CREATE OR ALTER PROCEDURE TOTAL_SALARY_BY_EMPLOYEES_LIST
--@emh AS empMonth READONLY
--AS
--BEGIN
--    SET NOCOUNT ON;
--    SELECT SUM(salary) FROM employee;
--END

-- DATA