import { Client, logger, BasicAuthInterceptor } from 'camunda-external-task-client-js';
import open from 'open';

const basicAuthentication = new BasicAuthInterceptor({
  username: "demo",
  password: "demo"
});
// configuration for the Client:
//  - 'baseUrl': url to the Process Engine
//  - 'logger': utility to automatically log important events
//  - 'asyncResponseTimeout': long polling timeout (then a new request will be issued)
const config = { 
  baseUrl: 'http://localhost:8080/engine-rest', 
  use: logger, 
  asyncResponseTimeout: 10000,
  interceptors: basicAuthentication
 };

// create a Client instance with custom configuration
const client = new Client(config);

// susbscribe to the topic: 'charge-card-topic'
client.subscribe('charge-card-topic', async function({ task, taskService }) {
  // Put your business logic here

  // Get a process variable
  const amount = task.variables.get('amount');
  const item = task.variables.get('item');

  console.log(`Charging credit card with an amount of ${amount}€ for the item '${item}'...`);
  
  open('https://docs.camunda.org/get-started/quick-start/success');
  
  // Complete the task
  await taskService.complete(task);
});
