export type SiteConfig = typeof siteConfig;

export const siteConfig = {
  name: "Vite + HeroUI",
  description: "Make beautiful websites regardless of your design experience.",
  navItems: [
    {
      label: "Главная",
      href: "/",
    },
    {
      label: "Расшифровки",
      href: "/workspace",
    },
  ],
  navMenuItems: [
    {
      label: "Главная",
      href: "/",
    },
    {
      label: "Расшифровки",
      href: "/workspace",
    },
  ],
  links: {
    github: "https://github.com/ivamly/mobileAssistant",
  },
};
