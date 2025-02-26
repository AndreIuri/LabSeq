# LabSeq
LabSeq sequence exercise

LabSeq backend configured to run in "http://localhost:8080/"
LabSeq frontend configured to run in "http://localhost:4200/"

In backend cors mapping config allowed frontend url "http://localhost:4200" and in frontend cors config allowed the url backend

Swagger run under http://localhost:8080/swagger-ui/index.html

LabSeqController contains two requests:
-> <url>/labseq/{n} where the labseq value for the position(n) is returned
-> <url>/labseq/all where the labseq sequence obtained is retrieved, the sequence is stored to save the reached progress using a cache method (hashmap)

Labseq method uses a hashmap to save all calculated values that will be needed for further calculations. The calculation method is based on a iterative approach.

node-modules folder in LabSeq frontend was git ignored

Frontend presents some validations directly present both in input (min and max position allowed) values as in requests processing, where possible errors are catched

Some backend tests were performed under spring boot test folder, where performance value was tested to check if l(10000) returned under 10 seconds (testGetValuePerformance)

