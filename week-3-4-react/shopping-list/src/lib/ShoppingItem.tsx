import type { ShoppingItem } from './ShoppingApp';

interface ShoppingItemProps {
  shoppingItem: ShoppingItem;
}

export function ShoppingItem({ shoppingItem }: ShoppingItemProps) {
  return (
    <div style={{ border: '1px solid white', padding: '1rem' }}>
      <h2>{shoppingItem.name}</h2>
    </div>
  );
}
