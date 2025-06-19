import { useState } from "react";
import "./App.css";

function App() {
  const [count, setCount] = useState<number>(0);

  const increment = () => {
    setCount(count + 1);
  };

  const decrement = () => {
    if (count > 0) setCount(count - 1);
  };

  return (
    <>
      <div>
        <h1>Counter App</h1>
        <p>Current Count: {count}</p>
        <div className="button-spacing">
          <button className="bg-green" onClick={increment}>
            Increment
          </button>
          <button onClick={decrement}>Decrement</button>
          <button className="bg-red" onClick={() => setCount(0)}>
            Reset
          </button>
        </div>
      </div>
    </>
  );
}

export default App;
