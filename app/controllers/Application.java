package controllers;

import actors.*;
import akka.actor.*;
import akka.actor.ActorRef;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import play.libs.Akka;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import play.Play;

/**
 * The main web controller that handles returning the index page, setting up a WebSocket, and watching a stock.
 */
public class Application extends Controller {

    public Result index() {
        return ok(views.html.index.render());
    }

    public WebSocket<JsonNode> ws() {
        return WebSocket.whenReady((in, out) -> {
            // create a new UserActor and send message to the client
            final ActorRef userActor = Akka.system().actorOf(Props.create(UserActor.class, out));
        });
    }

}
