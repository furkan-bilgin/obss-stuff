import type { Basket } from './ShoppingApp';

interface ShoppingTitleProps {
  basket: Basket;
}

export function ShoppingTitle(props: ShoppingTitleProps) {
  let itemCount = 0;
  for (const category in props.basket) {
    itemCount += props.basket[category].length;
  }
  return (
    <div style={{ border: '1px solid white', padding: '1rem' }}>
      <h1>My Shopping List</h1>
      <p>
        Total Number of Items: <strong>{itemCount}</strong>
      </p>
    </div>
  );
}
