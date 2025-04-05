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
      label: "Рабочее пространство",
      href: "/workspace",
    },
    {
      label: "Задачи",
      href: "/task",
    },
  ],
  navMenuItems: [
    {
      label: "Главная",
      href: "/",
    },
    {
      label: "Рабочее пространство",
      href: "/workspace",
    },
    {
      label: "Задачи",
      href: "/task",
    },
  ],
  links: {
    github: "https://github.com/ivamly/mobileAssistant",
  },
};
