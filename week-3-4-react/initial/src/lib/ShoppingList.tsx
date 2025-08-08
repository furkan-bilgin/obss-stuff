import type { Basket } from './ShoppingApp';
import { ShoppingItem } from './ShoppingItem';

interface ShoppingListProps {
  basket: Basket;
}

export function ShoppingList({ basket }: ShoppingListProps) {
  return (
    <div
      style={{
        border: '1px solid white',
        padding: '1rem',
        textAlign: 'start',
      }}
    >
      <h2>Shopping List</h2>
      <ul>
        {Object.entries(basket).map(([category, items]) => (
          <li key={category}>
            <h3>{category}</h3>
            <ol>
              {items.map((item) => (
                <li>
                  <ShoppingItem shoppingItem={item} />
                </li>
              ))}
            </ol>
          </li>
        ))}
      </ul>
    </div>
  );
}
