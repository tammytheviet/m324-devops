import logo from './logo.svg';
import './App.css';
import React from 'react';
/** 
 * ==================================
 * Gemeinsames Basisprojekt: ToDo-App
 * ================================== 
 * Die Todo-App basiert auf dem React-Framework und Spring-Framework mit Rest-API (Deploy on JAR -> Docker).
 * 
 * Execute and build information:
 *  - Frontend Start: npm start  (Terminalbefehl im Forntend Verzeichnis)
 *  - Backend Start: EE Eclipse-Projekt -> maven build -> spring-boot:run oder JAR auf docker mit Java 8 oder höher
 *  - Browser: localhost:3000
 * 
 * Aktuelle Featurelist: 
 *  - Singlepage App 
 *  - neues Todo in Textfeld eingeben, submit zum Speichern und direkt als Liste in der Eingabereihenfolge anzeigen 
 *  - Speicherung zunächst nur "In memory"
 *  - im Moment nur ein Text Eingabefeld für die ToDo Beschreibung   
 *  - alle offenen Todos werden als Liste angezeigt, jedes Todo hat einen Button zum "abschliessen" und 
 *    werden dabei definitiv und ohne Bestätigung direkt gelöscht
 * 
 * Mögliche Erweiterungen für die Lernenden: 
 *  - Persistent storage (das MySQL Plugin ist im Spring-Framework bereits integriert)
 *  - Deadlines (duedate)
 *  - nicht löschen sondern mit Status (open, done) mit evtl. Anzeigefilter 
 *  - Sortieren nach Deadline
 *  - Desing Verbesserungen
 *  - ... 
 */
class App extends React.Component {

  constructor(props) {
    super(props);      // ensure that the constructor of the parent class (React.Component) is properly called.
    this.state = {
      todos: typeof props.todos === 'undefined' ? [] : props.todos,
      taskdescription: ""
    };
  }

  handleChange = (event) => {
    this.setState({ taskdescription: event.target.value });
  }

  handleSubmit = (event) => {
    event.preventDefault();
    console.log("Sending task description to Spring-Server: "+this.state.taskdescription);
    fetch("http://localhost:8080/tasks", { 
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ taskdescription: this.state.taskdescription }) 
    })
    .then(response => {
      console.log("Receiving answer after sending to Spring-Server: ");
      console.log(response);
      this.setState({
        todos: [...this.state.todos, {taskdescription: this.state.taskdescription}]
      });          
      this.setState({taskdescription: ""});
    })
    .catch(error => console.log(error))
  }

  handleClick = (taskdescription) => {
    console.log("Sending task description to delete on Spring-Server: "+taskdescription);
    fetch(`http://localhost:8080/delete`, { // API endpoint (the complete URL!) to delete an existing taskdescription in the list
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ taskdescription: taskdescription })
    })
    .then(response => {
      console.log("Receiving answer after deleting on Spring-Server: ");
      console.log(response);
      window.location.href = "/"; 
    })
    .catch(error => console.log(error))
  }

renderTasks = (todos) => {
  return (
    <ul>
      {todos.map((todo, index) => (
        <li key={todo.taskdescription}>
          {"Task " + (index+1) + ": "+ todo.taskdescription}
          <button onClick={this.handleClick.bind(this, todo.taskdescription)}>Done</button>
        </li>
      ))}
    </ul>
  );
}

componentDidMount = () => {
  fetch("http://localhost:8080")    // API endpoint (the complete URL!) to get a taskdescription-list
    .then(response => response.json())
    .then(data => {
      console.log("Receiving task list data from Spring-Server: ");
      console.log(data);
      this.setState({todos: data});  // set the whole list at once
    })
    .catch(error => console.log(error))
}

render = () => {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1>
          ToDo Liste
        </h1>
        <form onSubmit={this.handleSubmit}>
          <input
            type="text"
            value={this.state.taskdescription}
            onChange={this.handleChange}
          />
          <button type="submit">Absenden</button>
        </form>
        <div>
          {this.renderTasks(this.state.todos)}
        </div>
      </header>
    </div>
  );
}
}
export default App;
