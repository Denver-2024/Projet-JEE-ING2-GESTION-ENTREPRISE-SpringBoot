CREATE TABLE absences (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    reason TEXT,
    expected BOOLEAN NOT NULL DEFAULT FALSE,
    is_paid_leave BOOLEAN NOT NULL DEFAULT FALSE,
    last_update DATETIME NOT NULL,

    CONSTRAINT fk_absence_employee
        FOREIGN KEY (employee_id) REFERENCES employees(id)
            ON DELETE CASCADE
);

CREATE TABLE bonuses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    reason TEXT,
    amount FLOAT NOT NULL,
    award_date DATE NOT NULL,
    last_update DATETIME NOT NULL,

    CONSTRAINT fk_bonus_employee
        FOREIGN KEY (employee_id) REFERENCES employees(id)
            ON DELETE CASCADE
);