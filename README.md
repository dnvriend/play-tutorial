Playframework-tutorial
======================
Before beginning analyzing this branch, please read:

* [Play - Handling asynchronous results](https://www.playframework.com/documentation/2.3.x/ScalaAsync)

# Introduction
The most important part to take away from the text is the following:

* Don't ever block,
* When you need to block, create a custom execution context schedule Futures to run from that one,
* When using JDBC databases, use Postgres :) and use [Maurício Linhares - Postgres Async driver](https://github.com/mauricio/postgresql-async),
* Always import:

        import play.api.libs.concurrent.Execution.Implicits.defaultContext

* There is only a single kind of Action (remember, controllers are Action generators?), which is asynchronous, so there
 is no blocking (synchronous) Action. So all actions we created earlier are non-blocking
 
        val echo = Action { request =>
          Ok("Got request [" + request + "]")
        }
 
* Use the `Action.async` builder when you use APIs that return a `scala.concurrent.Future`.

        import play.api.libs.concurrent.Execution.Implicits.defaultContext
        import scala.concurrent.Future
        
        def index = Action.async {
          val futureInt = Future { intensiveComputation() }
          futureInt.map(i => Ok("Got result: " + i))
        }

* Timeouts should be handled propertly:

        import play.api.libs.concurrent.Execution.Implicits.defaultContext
        import scala.concurrent.duration._
        
        def index = Action.async {
          val futureInt = scala.concurrent.Future { intensiveComputation() }
          val timeoutFuture = play.api.libs.concurrent.Promise.timeout("Oops", 1.second)
          Future.firstCompletedOf(Seq(futureInt, timeoutFuture)).map {
            case i: Int => Ok("Got result: " + i)
            case t: String => InternalServerError(t)
          }
        }