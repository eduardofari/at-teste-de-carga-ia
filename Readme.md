[![Build Status](http://172.16.1.210:8080/job/at-consultagto/badge/icon)](http://172.16.1.210:8080/job/at-consultagto/)
[![Quality Gate Status](http://sonar.odontoprev.com.br/api/project_badges/measure?project=br.com.odontoprev.quality%3Aat-consultagto&metric=alert_status)](http://sonar.odontoprev.com.br/dashboard?id=br.com.odontoprev.quality%3Aat-consultagto)
[![Build Status](http://172.16.1.210:8080/job/at-sistemasportais-status/badge/icon?subject=QA&status=Joao%20Fialho&color=darkblue)](http://git.odontoprev.com.br/odp108603)

### Cobertura dos testes: ###

* Consulta de GTO Disque
* Consulta de GTO Pré Aprovação
* Consulta de GTO Rede UNNA

### Tecnologias utilizadas: ###

* Java 8 JDK1.8.0_201
* Maven 3.3.9
* JUnit 4.12
* Cucumber 4.3.1

### Instruções de execução: ###

Na pasta raiz do projeto, onde encontra-se o POM.xml execute o comando
```
mvn clean test -Dfile.encoding="UTF-8" -Dambiente.teste="hml" -Dcucumber.options="classpath:features"
```
Para ignorar a execução de alguma tag, utilize o comando a baixo, substitiuindo o Ignore pelo nome da tag desejada
```
mvn clean test -Dfile.encoding="UTF-8" -Dambiente.teste="hml" -Dcucumber.options="classpath:features --tags ~@Ignore"
```
Para execução de uma tag específica, utilize o comando abaixo, substituindo o Execute pelo nome da tag desejada
```
mvn clean test -Dfile.encoding="UTF-8" -Dambiente.teste="hml" -Dcucumber.options="classpath:features --tags @Execute"