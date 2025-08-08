import type { Basket } from './ShoppingApp';

interface ShoppingTitleProps {
  basket: Basket;
}

export function ShoppingTitle(props: ShoppingTitleProps) {
  let itemCount = 0;
  for (const category of props.basket.shoppingCategories) {
    itemCount += category.items.length;
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
