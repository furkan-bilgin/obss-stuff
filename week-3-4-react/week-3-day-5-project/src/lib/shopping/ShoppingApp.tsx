import { ShoppingList } from './ShoppingList';
import { ShoppingTitle } from './ShoppingTitle';

export interface Basket {
  shoppingCategories: ShoppingCategory[];
}

export interface ShoppingCategory {
  name: string;
  items: ShoppingItem[];
}

export interface ShoppingItem {
  name: string;
}

const basket: Basket = {
  shoppingCategories: [
    {
      name: 'Food',
      items: [{ name: 'Apple' }, { name: 'Bread' }, { name: 'Cheese' }],
    },
    {
      name: 'Clothes',
      items: [{ name: 'Shirt' }, { name: 'Pants' }, { name: 'Hat' }],
    },
    {
      name: 'Supplies',
      items: [{ name: 'Pen' }, { name: 'Paper' }, { name: 'Glue' }],
    },
  ],
};

export function ShoppingApp() {
  return (
    <>
      <ShoppingTitle basket={basket} />
      <ShoppingList basket={basket} />
    </>
  );
}
