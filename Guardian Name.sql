select guardian_Fname, guardian_Lname, guardianPhone
from guardian g, patient p
where g.patient_ssn = p.P_ssn;