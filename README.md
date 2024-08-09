When you run the `build` Gradle task, the main module requests for a base jar from the `:module`: `module.jar` archive.

Whenever you uncomment the [`build.gradle.kts:17`](./build.gradle.kts), the `composed-jar` variant is resolved from the `:module`: `module-composed.jar` archive.

https://github.com/hsz/external-libraries-variats/blob/c3b318ca18086d93c003afd38797df3ad86810c2/build.gradle.kts#L17

As a side effect, when you refresh the Gradle project, the `module-composed.jar` appears in the _External Libraries_ tree:

![img.png](img.png)
