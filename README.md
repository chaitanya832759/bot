Bot Application
This project uses the Ollama local Llama3.2:1b model to handle natural language processing tasks.


**Available Commands:**
  serve       Start ollama
  create      Create a model from a Modelfile
  show        Show information for a model
  run         Run a model
  stop        Stop a running model
  pull        Pull a model from a registry
  push        Push a model to a registry
  list        List models
  ps          List running models
  cp          Copy a model
  rm          Remove a model
  help        Help about any command

Flags:
  -h, --help      help for ollama
  -v, --version   Show version information
Using Postman to Access the Application
You can interact with the bot via Postman by sending requests to the provided API endpoint.

**API Endpoint**
POST http://localhost:9090/api/ollama/chat
Request Body (JSON)


**Input example:**

**json**
{
  "prompt": "I am good, what about you"
}

**Expected Response**
**json**
{
  "response": "I'm just a language model, I don't have feelings or emotions like humans do, but I'm always here and ready to help with any questions or topics you'd like to discuss. How about you? Anything exciting happening in your life?"
}
