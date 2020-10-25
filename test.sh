# TODO define test set and read in
./cascrit-cli.sh collection create -file asdf.json
./cascrit-cli.sh collection read -file asdf.json
./cascrit-cli.sh collection create -file qwerty.json
./cascrit-cli.sh collection update -file asdf.json -new qwerty.json
./cascrit-cli.sh collection delete -file asdf.json
./cascrit-cli.sh collection delete -file qwerty.json
