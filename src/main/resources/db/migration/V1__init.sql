CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(100) NOT NULL UNIQUE,
    gender VARCHAR(20),
    type ENUM('JUNIOR', 'INTERMEDIATE', 'SENIOR') NOT NULL,
    salary FLOAT NOT NULL,
    password VARCHAR(255) NOT NULL,
    department_id INT
) AUTO_INCREMENT = 10000;

CREATE TABLE departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    head_employee_id INT NOT NULL,

    CONSTRAINT fk_department_head
        FOREIGN KEY (head_employee_id) REFERENCES employees(id)
);

CREATE TABLE projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    state ENUM('IN_PROGRESS', 'CANCELED', 'FINISHED') NOT NULL,
    department_id INT NOT NULL,

    CONSTRAINT fk_project_department
        FOREIGN KEY (department_id) REFERENCES departments(id)
            ON DELETE CASCADE
);

-- On ajoute les departements aux employés que maintenant car sinon
-- on a un problème TROP CHIANT de dépendance circulaire...
ALTER TABLE employees
    ADD CONSTRAINT fk_employee_department
        FOREIGN KEY (department_id) REFERENCES departments(id)
            ON DELETE SET NULL;