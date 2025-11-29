CREATE TABLE roles (
    name VARCHAR(50) PRIMARY KEY
);

INSERT INTO roles (name) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_HR'),
    ('ROLE_MANAGER'),
    ('ROLE_EMPLOYEE');

CREATE TABLE employee_roles (
    employee_id INT NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (employee_id, role_name),
    CONSTRAINT fk_user_role_emp FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_role_name FOREIGN KEY (role_name) REFERENCES roles(name) ON DELETE CASCADE
);

INSERT INTO employee_roles (employee_id, role_name)
SELECT id, 'ROLE_EMPLOYEE' FROM employees;