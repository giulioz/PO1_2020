import React, { useEffect, useRef, useState } from "react";
import ReactDOM from "react-dom";
import "./style.css";

interface Board {
  data: string[][];
  dimension: number;
  full: boolean;
}

interface Player {
  ownMark: string;
}

interface GameState {
  currentBoard: Board;
  lastPlayer: Player;
  playerA: Player;
  playerB: Player;
  winner: string;
}

function useGameState() {
  const ws = useRef<WebSocket | null>(null);
  const [gameState, setGameState] = useState<null | GameState>(null);

  useEffect(() => {
    if (ws.current === null) {
      ws.current = new WebSocket("ws://localhost:8080");
      ws.current.onmessage = (message) => {
        const payload = JSON.parse(message.data);
        setGameState(payload);
      };
    }
  }, []);

  return gameState;
}

function App() {
  const gameState = useGameState();

  return (
    <>
      <h1>Tic Tac Toe</h1>
      {gameState && (
        <div className="container">
          {gameState.currentBoard.data.map((row, i) => (
            <div key={i}>
              {row.map((cell, i) => (
                <div className="cell" key={i}>{cell}</div>
              ))}
            </div>
          ))}
        </div>
      )}
      {/* <button>Next Move</button> */}
    </>
  );
}

const element = document.getElementById("root");
ReactDOM.render(<App />, element);
