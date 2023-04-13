# Reto Final DevOps

> *“El único lugar en que el éxito está antes que el trabajo, es en el diccionario”*
>
> Vincent Thomas Lombardi

#### **La nota es Grupal**

## Requerimientos:

1. Crear un repositorio público en [GitHub](https://github.com/) llamado `retoQaDevOps2023C1G#` (donde # es el grupo
   asignado).
2. El modelo de ramas debe ser el modelo `Trunk Based Development`. Hacer que exista una rama principal llamada `trunk`
   y que además esté protegida para que nadie le haga un push y así garantizar el flujo (la rama `trunk` solo debe
   aceptar mezclas desde los features y nada más). También, tener presente que solo deben existir ramas de
   tipo `feature/*` o la rama `trunk` en el repositorio remoto.
3. Agregar a su Coach como colaborador en su proyecto con el rol de desarrollador.
4. Idear en grupo, automatizaciones con Serenity BDD y Cucumber para servicios web REST o SOAP (o de las 2) en un
   proyecto de automatización común y realizarlas bajo la metodología de trabajo con `Trunk Based Development`. Mínimo 4
   escenarios de prueba automáticos por cada integrante.
   > Se deben sincronizar muy bien porque deben usar clases comunes como utilidades, constantes, etc. La idea es
   minimizar la duplicidad de código fuente y ojalá a CERO % (SonarQube mide porcentaje de código duplicado). **LAS
   AUTOMATIZACIONES DEBEN SOPORTAR EJECUCIONES EN WINDOWS Y LINUX**.
5. Recuerde realizar un muy buen plan de calidad general y uno individual (detallado) de lo que realizará cada
   integrante, es muy importante el orden. Solo se aceptan archivos PDF.
6. Con ayuda de Jenkins, asegurar que se ejecute un pipeline en la rama `feature/*` (que se entiende como de tipo CI)
   que compile y ejecute los test del código fuente que cada uno de ustedes sube al repositorio remoto, para asegurar el
   código de sus respectivos features. Además, hacer que se envíe una notificación del resultado del pipeline a quien
   hizo el push que activa el pipeline (El correo de todos los integrantes del equipo).
7. Con ayuda de Jenkins, hay que asegurar que se ejecute un pipeline en la rama `trunk` (que se entiende como de tipo
   CD) que compile el código fuente que cada uno de ustedes mezcla a la rama `trunk` mediante el repositorio remoto.
   También es necesario que en este punto del CD se ejecute un análisis de Sonar sobre la rama `trunk`.

   Además, hacer que se envíe una notificación del resultado de la ejecución del pipeline a todos los miembros del
   equipo (puede ser un correo). No olvidar rescatar y enviar, como crean mejor, el informe de ejecución de pruebas que
   genera Serenity BDD o el resumen de la ejecución número de test ejecutados, número de test aprobados etc.

## Consideraciones

- Una vez se haga la entrega final, el repositorio será descargado en una máquina GNU/Linux y debe correr por el
  terminal sin problemas.
- Para realizar la ejecución de los pipelines debe exponer (acceso público) dos puertos locales, en dos computadores
  diferentes, o debe exponer un puerto local (acceso público) y el requerimiento adicional en el puerto local del mismo
  computador.
- Recuerde ajustar el Sonar.

### Calificación, es la misma nota para el grupo

- Entrega funcional al 100% (CI/CD) → 50%
- Planes de calidad general e individuales → 15%
- Correr en GNU/Linux y pasar el 100% de los test → 15%
- Correo con el reporte de Serenity → 10%
- Valor agregado → 10%

- - -
*By: Team 2 🐢*