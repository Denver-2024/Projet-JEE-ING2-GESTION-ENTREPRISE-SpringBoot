CREATE TABLE payroll_deductions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    amount FLOAT NOT NULL,
    type ENUM('FIXED', 'PERCENTAGE') NOT NULL
);