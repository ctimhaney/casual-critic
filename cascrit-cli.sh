JCLASSPATH="lib/*"
JCLASSPATH="$JCLASSPATH:cascrit-cli/build/libs/*"
JCLASSPATH="$JCLASSPATH:cascrit-common/build/libs/*"
java -cp $JCLASSPATH com.ctimhaney.dev.cascrit.cli.CasualCriticCLI "$@"
