import { Component } from 'react';

export class IncrementalButtonComponent extends Component<
  object,
  { count: number }
> {
  state = {
    count: 0,
  };

  increment = () => {
    this.setState((prevState) => ({
      count: prevState.count + 1,
    }), () => {
      alert(this.state.count);
    });
  };

  render() {
    return (
      <>
        <button onClick={this.increment}>Increment</button>
        <span style={{ marginLeft: '10px' }}>{this.state.count}</span>
      </>
    );
  }
}
