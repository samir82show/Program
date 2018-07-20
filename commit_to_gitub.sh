#!/usr/bin/sh

if [ $# -ne 1 ]; then
	echo "Usage: $0 Gitub_repo"
	exit 1
fi

repo="$1"
echo "# $repo" >> README.md
if [ ! -e ".git" ]; then
	git init
	git add README.md
fi
git add .
git commit -m "$repo `date`"
git remote add origin https://github.com/samir82show/${repo}.git
git push -u origin master
