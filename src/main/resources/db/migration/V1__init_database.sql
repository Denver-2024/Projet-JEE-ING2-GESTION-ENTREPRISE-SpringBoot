CREATE TABLE roles (
    name VARCHAR(50) PRIMARY KEY
);
INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_HR'), ('ROLE_MANAGER'), ('ROLE_EMPLOYEE');

CREATE TABLE departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(100) NOT NULL UNIQUE,
    gender VARCHAR(20),
    type ENUM('JUNIOR', 'INTERMEDIATE', 'SENIOR') NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    password VARCHAR(255) NOT NULL,
    department_id INT,

    CONSTRAINT fk_employee_department
        FOREIGN KEY (department_id) REFERENCES departments(id)
        ON DELETE SET NULL
) AUTO_INCREMENT = 10000;

CREATE TABLE department_heads (
    department_id INT NOT NULL,
    employee_id INT NOT NULL,

    PRIMARY KEY (department_id),
    UNIQUE (employee_id),

    CONSTRAINT fk_dh_dept FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE CASCADE,
    CONSTRAINT fk_dh_emp FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

CREATE TABLE employee_roles (
    employee_id INT NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (employee_id, role_name),
    CONSTRAINT fk_user_role_emp FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_role_name FOREIGN KEY (role_name) REFERENCES roles(name) ON DELETE CASCADE
);

CREATE TABLE projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    state ENUM('IN_PROGRESS', 'CANCELED', 'FINISHED') NOT NULL,
    department_id INT NOT NULL,
    CONSTRAINT fk_project_department FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE CASCADE
);

CREATE TABLE absences (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    reason TEXT,
    expected BOOLEAN NOT NULL DEFAULT FALSE,
    is_paid_leave BOOLEAN NOT NULL DEFAULT FALSE,
    last_update DATETIME NOT NULL,
    CONSTRAINT fk_absence_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

CREATE TABLE bonuses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    reason TEXT,
    amount DECIMAL(10, 2) NOT NULL,
    award_date DATE NOT NULL,
    last_update DATETIME NOT NULL,
    CONSTRAINT fk_bonus_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

CREATE TABLE payroll_deductions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    amount DECIMAL(10, 2) NOT NULL,
    type ENUM('FIXED', 'PERCENTAGE') NOT NULL
);