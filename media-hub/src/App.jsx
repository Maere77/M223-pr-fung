import RegisterForm from './components/RegisterForm';
import ThemeToggle from './components/ThemeToggle';
import './styles/globals.css';
import './styles/App.css';
import LoginForm from "./components/LoginForm.jsx";

function App() {
  return (
    <div className="app">
      <ThemeToggle />
      <LoginForm />
    </div>
  );
}

export default App;
