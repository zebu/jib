/*
 * Copyright 2020 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.cloud.tools.jib.gradle;

import groovy.lang.Closure;
import java.io.File;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.FileSystemLocation;
import org.gradle.api.file.FileTree;
import org.gradle.api.provider.Provider;
import org.gradle.api.specs.Spec;
import org.gradle.api.tasks.TaskDependency;

/** Implementation of {@link FileCollection} that just holds a set of {@link File}s. */
class TestFileCollection implements FileCollection {

  private Set<File> files;

  TestFileCollection(Set<Path> files) {
    this.files = files.stream().map(Path::toFile).collect(Collectors.toSet());
  }

  private TestFileCollection(Set<File> files, boolean ignored) {
    this.files = files;
  }

  @Override
  public Set<File> getFiles() {
    return files;
  }

  @Override
  public boolean isEmpty() {
    return files.isEmpty();
  }

  @Override
  public Iterator<File> iterator() {
    return files.iterator();
  }

  @Override
  public boolean contains(File file) {
    return files.contains(file);
  }

  @Override
  public FileCollection minus(FileCollection collection) {
    Set<File> filtered =
        files.stream().filter(file -> !collection.contains(file)).collect(Collectors.toSet());
    return new TestFileCollection(filtered, false);
  }

  @Override
  public FileCollection filter(Spec<? super File> filterSpec) {
    Set<File> filtered =
        files.stream().filter(filterSpec::isSatisfiedBy).collect(Collectors.toSet());
    return new TestFileCollection(filtered, false);
  }

  @Override
  public FileCollection filter(@SuppressWarnings("rawtypes") Closure filterClosure) {
    throw new UnsupportedOperationException();
  }

  @Override
  public TaskDependency getBuildDependencies() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object addToAntBuilder(Object builder, String nodeName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addToAntBuilder(Object builder, String nodeName, AntType type) {
    throw new UnsupportedOperationException();
  }

  @Override
  public FileTree getAsFileTree() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getAsPath() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Provider<Set<FileSystemLocation>> getElements() {
    throw new UnsupportedOperationException();
  }

  @Override
  public File getSingleFile() throws IllegalStateException {
    throw new UnsupportedOperationException();
  }

  @Override
  public FileCollection plus(FileCollection collection) {
    throw new UnsupportedOperationException();
  }
}
