select Fname, Lname, guardian_phone
from guardian g, patient p
where g.patient_ssn = p.ssn