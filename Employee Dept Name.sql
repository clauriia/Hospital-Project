select Dept_Name, deptHead_SSN, Fname, Lname 
from department d, employee e
where d.DeptId = e.deptID AND deptHead_SSN =ssn;