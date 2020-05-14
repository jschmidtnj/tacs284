#!/bin/bash

set -e

if [ -z "$1" ]; then
  echo "cannot find input directory"
  exit 1
else
  input_dir=$1
fi

first_file=AdvBalancedParenTree.java
second_file=BalancedParenTree.java
output_dir=src/main/java/grading
res_dir=./output

rm -rf "$res_dir"/*

printf "name,average\n" > $res_dir/stats.csv

count_outputs=0

for dir in "$input_dir"/*
do
  name=${dir%*/}
  name=${dir##*/}
  if [ ! -f "$dir"/"$first_file" ] && [ ! -f "$dir"/"$second_file" ]; then
    echo "necessary files don't exist for $name"
    continue
  fi
  # shellcheck disable=SC2012
  count_pdf=$(ls -al "$dir"/*.pdf 2>/dev/null | wc -l)
  if [ "$count_pdf" -gt 0 ]
  then
    extra=true
  else
    extra=false
  fi
  # shellcheck disable=SC2115
  rm -rf "$output_dir"/*
  cp "$dir"/*.java "$output_dir"/
  for file in "$output_dir"/*.java
  do
    sed -i '/package */d' "$file"
    sed -i '1ipackage grading;' "$file"
  done
  echo "Processing $name"
  # shellcheck disable=SC2035
  ./gradlew test --tests *runTest -Dname="$name" -Dextra="$extra" > /dev/null
  count_outputs=$((count_outputs + 1))
  echo "Done processing $name"
done

echo "number of outputs: $count_outputs"

./process_stats.py
