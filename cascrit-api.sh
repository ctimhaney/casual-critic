JCLASSPATH="lib/*"
JCLASSPATH="$JCLASSPATH:cascrit-api/build/libs/*"
JCLASSPATH="$JCLASSPATH:cascrit-common/build/libs/*"
# java -cp $JCLASSPATH com.ctimhaney.dev.cascrit.api.CasualCriticAPI "$@"
java -jar cascrit-api/build/libs/cascrit-api-0.0.1-SNAPSHOT.jar
