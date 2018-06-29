|    Metrics    |                                                                                     Master                                                                                     |                                                                                  Develop                                                                                 |
|:-------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| CI status     | [![pipeline status](https://gitlab.com/redmic-project/server/library/reports/badges/master/pipeline.svg)](https://gitlab.com/redmic-project/server/library/reports/commits/master) | [![pipeline status](https://gitlab.com/redmic-project/server/library/reports/badges/dev/pipeline.svg)](https://gitlab.com/redmic-project/server/library/reports/commits/dev) |
| Test coverage | [![coverage report](https://gitlab.com/redmic-project/server/library/reports/badges/master/coverage.svg)](https://gitlab.com/redmic-project/server/library/reports/commits/master) | [![coverage report](https://gitlab.com/redmic-project/server/library/reports/badges/dev/coverage.svg)](https://gitlab.com/redmic-project/server/library/reports/commits/dev) |

# API Reports
Este proyecto sirve de apoyo a la API de REDMIC, añadiendo la funcionalidad de generar informes personalizados.

Para ello, se sirve de la plataforma BIRT y de su API Runtime, para integrarlo en los controladores de la API de REDMIC.


## Crear un nuevo informe
Se ha intentado integrar BIRT Framework en Spring, para diseñar los informes desde el mismo IDE que usamos normalmente, pero hay fallos relacionados con la instalación.
Por el momento, descargamos la versión completa de BIRT con su Eclipse integrado, listo para funcionar.

Al crear un nuevo informe, lo primero es añadirle nuestra **RedmicLibrary** desde *Resource Explorer*, que incluye la *MasterPage*, *DataSources*, *DataSets*, etc.
Una vez añadida, podemos ir al elemento deseado dentro de ella para añadirlo al informe (no se añade todo automáticamente).
Por lo general, si queremos añadir algún recurso nuevo y es susceptible de reutilizarse en otro informe, lo añadiremos directamente en **RedmicLibrary**, para poderlo importar a otro informe posteriormente.

Nos basamos en POJO como fuente de datos, usando los modelos de ElasticSearch presentes en API (se separarán en otro proyecto a su vez).
Para que funcione todo correctamente, debemos importar las dependencias necesarias para estos modelos, que por ahora son *joda-time* y *models* (tanto para designtime como para runtime).

### Detalles a tener en cuenta

* A la hora de añadir la *MasterPage* de una librería al informe, parecerá que no funciona correctamente.
Esto es porque el informe ya incluye una por defecto, y tiene preferencia sobre la añadida. La única forma que se ha encontrado para solucionarlo es editar el código XML del informe a mano, borrando las *MasterPage* no deseadas.

* Muchas veces, aunque un *DataSet* esté disponible para un informe, no podremos acceder a los datos que contiene.
Esto sucede porque hay que hacer *Binding* de los elementos del informe con las propiedades que nos interesen del *DataSet*.
BIRT lo hace al autogenerar elementos, pero si los creamos desde cero, debemos hacerlo nosotros.

* Por lo general, BIRT no se actualiza las dependencias al cambiar el nombre de algún elemento. Por tanto, es más que recomendable dar un nombre significativo a las cosas en el momento de su creación, para no necesitar editar sus nombres posteriormente.


## Probar la generación de informes
Para agilizar las pruebas cuando se crea un nuevo diseño de informe, ejecutamos los tests del proyecto con Maven:
```
// ejecuta todos los tests que encuentre
mvn test
// ejecuta un fichero de tests concreto
mvn -Dtest=TestActivityReports test
// ejecuta un test específico de un fichero de tests concreto
mvn -Dtest=TestActivityReports#generateInfoReport test
```
Si todo ha funcionado correctamente, encontraremos los informes generados en el directorio *target* del proyecto.


## Integración con API REDMIC
Para usar este proyecto desde nuestra API, lo instalamos en el repositorio Maven local:
```
mvn install
```
El *pom.xml* de API ya está preparado para hacer uso de él, incluyendo los diseños.

Es importante recordar que, antes de usar los informes en API, se debe eliminar las dependencias importadas al diseñarlos (o importarlos en su momento de manera absoluta), ya que no son necesarios (API ya incluye en su classpath todo lo necesario) y generan errores en BIRT.