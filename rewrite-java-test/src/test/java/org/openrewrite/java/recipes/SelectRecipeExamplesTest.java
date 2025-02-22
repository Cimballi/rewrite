/*
 * Copyright 2023 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.java.recipes;

import org.junit.jupiter.api.Test;
import org.openrewrite.java.JavaParser;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

class SelectRecipeExamplesTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new SelectRecipeExamples())
          .parser(JavaParser.fromJavaVersion()
            .classpath(JavaParser.runtimeClasspath()));
    }

    @Test
    void selectFirstExample() {
        rewriteRun(
          java(
            """
              package org.openrewrite.java.cleanup;
             
              import org.junit.jupiter.api.Test;
              import org.openrewrite.Recipe;
              import org.openrewrite.test.RecipeSpec;
              import org.openrewrite.test.RewriteTest;
             
              import static org.openrewrite.java.Assertions.java;
              
              class ChainStringBuilderAppendCallsTest implements RewriteTest {
                  @Override
                  public void defaults(RecipeSpec spec) {
                      Recipe recipe = new ChainStringBuilderAppendCalls();
                      spec.recipe(recipe);
                  }

                  @Test
                  void test1() {
                      rewriteRun(
                        java(
                          \"""
                              BEFORE
                          \""",
                          \"""
                              AFTER
                          \"""
                        )
                      );
                  }

                  @Test
                  void test2() {
                      rewriteRun(
                        java(
                          \"""
                              BEFORE
                          \""",
                          \"""
                              AFTER
                          \"""
                        )
                      );
                  }
              }
              """,
            """
              package org.openrewrite.java.cleanup;
             
              import org.junit.jupiter.api.Test;
              import org.openrewrite.Recipe;
              import org.openrewrite.internal.DocumentExample;
              import org.openrewrite.test.RecipeSpec;
              import org.openrewrite.test.RewriteTest;
             
              import static org.openrewrite.java.Assertions.java;
              
              class ChainStringBuilderAppendCallsTest implements RewriteTest {
                  @Override
                  public void defaults(RecipeSpec spec) {
                      Recipe recipe = new ChainStringBuilderAppendCalls();
                      spec.recipe(recipe);
                  }

                  @DocumentExample
                  @Test
                  void test1() {
                      rewriteRun(
                        java(
                          \"""
                              BEFORE
                          \""",
                          \"""
                              AFTER
                          \"""
                        )
                      );
                  }

                  @Test
                  void test2() {
                      rewriteRun(
                        java(
                          \"""
                              BEFORE
                          \""",
                          \"""
                              AFTER
                          \"""
                        )
                      );
                  }
              }
              """
          )
        );
    }

    @Test
    void skipNotChangedTest() {
        rewriteRun(
          java(
            """
              package org.openrewrite.java.cleanup;
             
              import org.junit.jupiter.api.Test;
              import org.openrewrite.Recipe;
              import org.openrewrite.test.RecipeSpec;
              import org.openrewrite.test.RewriteTest;
             
              import static org.openrewrite.java.Assertions.java;
              
              class ChainStringBuilderAppendCallsTest implements RewriteTest {
                  @Override
                  public void defaults(RecipeSpec spec) {
                      Recipe recipe = new ChainStringBuilderAppendCalls();
                      spec.recipe(recipe);
                  }

                  @Test
                  void test1() {
                      rewriteRun(
                        java(
                          \"""
                              BEFORE
                          \"""
                        )
                      );
                  }

                  @Test
                  void test2() {
                      rewriteRun(
                        java(
                          \"""
                              BEFORE
                          \""",
                          \"""
                              AFTER
                          \"""
                        )
                      );
                  }
              }
              """,
            """
              package org.openrewrite.java.cleanup;
             
              import org.junit.jupiter.api.Test;
              import org.openrewrite.Recipe;
              import org.openrewrite.internal.DocumentExample;
              import org.openrewrite.test.RecipeSpec;
              import org.openrewrite.test.RewriteTest;
             
              import static org.openrewrite.java.Assertions.java;
              
              class ChainStringBuilderAppendCallsTest implements RewriteTest {
                  @Override
                  public void defaults(RecipeSpec spec) {
                      Recipe recipe = new ChainStringBuilderAppendCalls();
                      spec.recipe(recipe);
                  }

                  @Test
                  void test1() {
                      rewriteRun(
                        java(
                          \"""
                              BEFORE
                          \"""
                        )
                      );
                  }

                  @DocumentExample
                  @Test
                  void test2() {
                      rewriteRun(
                        java(
                          \"""
                              BEFORE
                          \""",
                          \"""
                              AFTER
                          \"""
                        )
                      );
                  }
              }
              """
          )
        );
    }

    @Test
    void skipIssueAnnotatedTests() {
        rewriteRun(
          java(
            """
              package org.openrewrite.java.cleanup;
             
              import org.junit.jupiter.api.Test;
              import org.openrewrite.Issue;
              import org.openrewrite.Recipe;
              import org.openrewrite.test.RecipeSpec;
              import org.openrewrite.test.RewriteTest;
             
              import static org.openrewrite.java.Assertions.java;
              
              class ChainStringBuilderAppendCallsTest implements RewriteTest {
                  @Override
                  public void defaults(RecipeSpec spec) {
                      Recipe recipe = new ChainStringBuilderAppendCalls();
                      spec.recipe(recipe);
                  }

                  @Issue("https://github.com/openrewrite/rewrite/issues/x")
                  @Test
                  void test1() {
                      rewriteRun(
                        java(
                          ""\"
                              BEFORE
                          ""\",
                          ""\"
                              AFTER
                          ""\"
                        )
                      );
                  }

                  @Test
                  void test2() {
                      rewriteRun(
                        java(
                          ""\"
                              BEFORE
                          ""\",
                          ""\"
                              AFTER
                          ""\"
                        )
                      );
                  }
              }
              """,
            """
              package org.openrewrite.java.cleanup;
             
              import org.junit.jupiter.api.Test;
              import org.openrewrite.Issue;
              import org.openrewrite.Recipe;
              import org.openrewrite.internal.DocumentExample;
              import org.openrewrite.test.RecipeSpec;
              import org.openrewrite.test.RewriteTest;
             
              import static org.openrewrite.java.Assertions.java;
              
              class ChainStringBuilderAppendCallsTest implements RewriteTest {
                  @Override
                  public void defaults(RecipeSpec spec) {
                      Recipe recipe = new ChainStringBuilderAppendCalls();
                      spec.recipe(recipe);
                  }

                  @Issue("https://github.com/openrewrite/rewrite/issues/x")
                  @Test
                  void test1() {
                      rewriteRun(
                        java(
                          ""\"
                              BEFORE
                          ""\",
                          ""\"
                              AFTER
                          ""\"
                        )
                      );
                  }
                  
                  @DocumentExample
                  @Test
                  void test2() {
                      rewriteRun(
                        java(
                          ""\"
                              BEFORE
                          ""\",
                          ""\"
                              AFTER
                          ""\"
                        )
                      );
                  }
              }
              """
          )
        );
    }


    @Test
    void skipDisabledAnnotatedTests() {
        rewriteRun(
          java(
            """
              package org.openrewrite.java.cleanup;
             
              import org.junit.jupiter.api.Disabled;
              import org.junit.jupiter.api.Test;
              import org.openrewrite.Recipe;
              import org.openrewrite.test.RecipeSpec;
              import org.openrewrite.test.RewriteTest;
             
              import static org.openrewrite.java.Assertions.java;
              
              class ChainStringBuilderAppendCallsTest implements RewriteTest {
                  @Override
                  public void defaults(RecipeSpec spec) {
                      Recipe recipe = new ChainStringBuilderAppendCalls();
                      spec.recipe(recipe);
                  }

                  @Disabled("some reason")
                  @Test
                  void test1() {
                      rewriteRun(
                        java(
                          ""\"
                              BEFORE
                          ""\",
                          ""\"
                              AFTER
                          ""\"
                        )
                      );
                  }
              }
              """
          )
        );
    }

    @Test
    void ignoreIfHasAnnotated() {
        rewriteRun(
          java(
            """
              package org.openrewrite.java.cleanup;
             
              import org.junit.jupiter.api.Test;
              import org.openrewrite.Recipe;
              import org.openrewrite.internal.DocumentExample;
              import org.openrewrite.test.RecipeSpec;
              import org.openrewrite.test.RewriteTest;
             
              import static org.openrewrite.java.Assertions.java;
              
              class ChainStringBuilderAppendCallsTest implements RewriteTest {
                  @Override
                  public void defaults(RecipeSpec spec) {
                      Recipe recipe = new ChainStringBuilderAppendCalls();
                      spec.recipe(recipe);
                  }

                  @DocumentExample
                  @Test
                  void test1() {
                      rewriteRun(
                        java(
                          ""\"
                              BEFORE
                          ""\",
                          ""\"
                              AFTER
                          ""\"
                        )
                      );
                  }
              }
              """
          )
        );
    }

}
