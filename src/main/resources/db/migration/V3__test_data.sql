-- NOTE: JEU DE DONNEES GENERE PAR IA

-- Password for all users is: 'password'

-- 1. Insert Employees (initially without Department to avoid FK errors)
-- IDs chosen manually starting from 10001 to ensure consistency

-- Super Admin (No Dept)
INSERT INTO employees (id, first_name, last_name, email, address, gender, type, salary, password, department_id)
VALUES (10001, 'Alice', 'Admin', 'admin@cytech.fr', '1 Admin Street', 'Female', 'SENIOR', 90000,
        '$2a$12$AzX42X/gNMfIumvBCeDEvOKbhC3eT4zms1pYispuYSdorXBCQ/V2y', NULL);

-- HR Manager (No Dept)
INSERT INTO employees (id, first_name, last_name, email, address, gender, type, salary, password, department_id)
VALUES (10002, 'Bob', 'HumanResources', 'hr@cytech.fr', '2 HR Lane', 'Male', 'SENIOR', 75000,
        '$2a$12$AzX42X/gNMfIumvBCeDEvOKbhC3eT4zms1pYispuYSdorXBCQ/V2y', NULL);

-- IT Head
INSERT INTO employees (id, first_name, last_name, email, address, gender, type, salary, password, department_id)
VALUES (10003, 'Charlie', 'Tech', 'charlie@cytech.fr', '3 Server Rd', 'Male', 'SENIOR', 85000,
        '$2a$12$AzX42X/gNMfIumvBCeDEvOKbhC3eT4zms1pYispuYSdorXBCQ/V2y', NULL);

-- Sales Head
INSERT INTO employees (id, first_name, last_name, email, address, gender, type, salary, password, department_id)
VALUES (10004, 'Diana', 'Sales', 'diana@cytech.fr', '4 Market Blvd', 'Female', 'SENIOR', 82000,
        '$2a$12$AzX42X/gNMfIumvBCeDEvOKbhC3eT4zms1pYispuYSdorXBCQ/V2y', NULL);

-- Developer (Junior)
INSERT INTO employees (id, first_name, last_name, email, address, gender, type, salary, password, department_id)
VALUES (10005, 'Evan', 'Code', 'evan@cytech.fr', '5 Java Way', 'Male', 'JUNIOR', 45000,
        '$2a$12$AzX42X/gNMfIumvBCeDEvOKbhC3eT4zms1pYispuYSdorXBCQ/V2y', NULL);

-- Salesperson (Intermediate)
INSERT INTO employees (id, first_name, last_name, email, address, gender, type, salary, password, department_id)
VALUES (10006, 'Fiona', 'Deal', 'fiona@cytech.fr', '6 Cash Flow St', 'Female', 'INTERMEDIATE', 55000,
        '$2a$12$AzX42X/gNMfIumvBCeDEvOKbhC3eT4zms1pYispuYSdorXBCQ/V2y', NULL);


-- 2. Insert Departments (Linking to the Heads created above)
INSERT INTO departments (id, name, description, head_employee_id)
VALUES
    (1, 'IT Department', 'Handles software development and infrastructure', 10003),
    (2, 'Sales Department', 'Handles client acquisition and revenue', 10004);


-- 3. Update Employees to link them to the new Departments
-- Charlie and Evan -> IT (1)
UPDATE employees SET department_id = 1 WHERE id IN (10003, 10005);
-- Diana and Fiona -> Sales (2)
UPDATE employees SET department_id = 2 WHERE id IN (10004, 10006);


-- 4. Insert Roles (Linking Employees to Roles)
-- 10001 -> ADMIN
INSERT INTO employee_roles (employee_id, role_name) VALUES (10001, 'ROLE_ADMIN');
-- 10002 -> HR
INSERT INTO employee_roles (employee_id, role_name) VALUES (10002, 'ROLE_HR');
-- 10003 -> MANAGER (Department Head)
INSERT INTO employee_roles (employee_id, role_name) VALUES (10003, 'ROLE_MANAGER');
-- 10004 -> MANAGER (Department Head)
INSERT INTO employee_roles (employee_id, role_name) VALUES (10004, 'ROLE_MANAGER');
-- 10005 -> EMPLOYEE
INSERT INTO employee_roles (employee_id, role_name) VALUES (10005, 'ROLE_EMPLOYEE');
-- 10006 -> EMPLOYEE
INSERT INTO employee_roles (employee_id, role_name) VALUES (10006, 'ROLE_EMPLOYEE');


-- 5. Insert Projects
INSERT INTO projects (name, description, state, department_id)
VALUES
    ('Migration to Cloud', 'Moving legacy infrastructure to AWS', 'IN_PROGRESS', 1),
    ('Internal Wiki', 'Documentation for new hires', 'FINISHED', 1),
    ('Q4 Sales Push', 'End of year revenue maximization', 'IN_PROGRESS', 2),
    ('Cold Calling Campaign', 'Outreach to new region', 'CANCELED', 2);