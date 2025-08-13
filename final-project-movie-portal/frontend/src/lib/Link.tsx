import { Link as ReactRouterLink } from 'react-router-dom';

export const Link = ({
  to,
  children,
  className,
}: {
  to: string;
  children: React.ReactNode;
  className?: string;
}) => {
  const handleClick = (e: React.MouseEvent<HTMLAnchorElement, MouseEvent>) => {
    if (to !== window.location.pathname) {
      return;
    }
    e.preventDefault();
    window.location.reload();
  };
  return (
    <ReactRouterLink to={to} onClick={handleClick} className={className}>
      {children}
    </ReactRouterLink>
  );
};
