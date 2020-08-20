select P_Fname, P_Lname, Fname, Lname, EmployeeId
from employee e, patient p
where p.doctor_inchargeSSN = ssn; 