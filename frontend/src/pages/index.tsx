import DefaultLayout from "@/layouts/default";

import { Button, Card, Link } from "@heroui/react";
import { Icon } from "@iconify/react";
import { motion } from "framer-motion";

export default function IndexPage() {
  return (
    <DefaultLayout>
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <div className="text-center space-y-8">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
          >
            <h1 className="text-4xl md:text-6xl font-bold bg-gradient-to-r from-primary-500 to-secondary-500 bg-clip-text text-transparent">
              Мобильный Ассистент
            </h1>
            <p className="mt-4 text-xl text-default-600">
              Интеллектуальное распознавание речи и анализ встреч
            </p>
          </motion.div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mt-16">
            {features.map((feature, index) => (
              <motion.div
                key={feature.title}
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.6, delay: 0.2 * index }}
              >
                <Card className="p-6 text-center h-full" isHoverable>
                  <Icon
                    icon={feature.icon}
                    className="w-12 h-12 mx-auto text-primary-500 mb-4"
                  />
                  <h3 className="text-lg font-semibold mb-2">
                    {feature.title}
                  </h3>
                  <p className="text-default-500">{feature.description}</p>
                </Card>
              </motion.div>
            ))}
          </div>

          <motion.div
            initial={{ opacity: 0, scale: 0.9 }}
            animate={{ opacity: 1, scale: 1 }}
            transition={{ duration: 0.6, delay: 0.8 }}
            className="mt-12"
          >
            <Button
              size="lg"
              color="primary"
              elementType={Link}
              as={Link}
              href="/workspace"
              variant="shadow"
              endContent={<Icon icon="lucide:arrow-right" />}
              className="font-semibold"
            >
              Начать Работу
            </Button>
          </motion.div>

          <div className="relative">
            {[...Array(3)].map((_, i) => (
              <motion.div
                key={i}
                className="absolute hidden md:block"
                initial={{ opacity: 0 }}
                animate={{
                  opacity: [0.3, 0.6, 0.3],
                  x: [0, 20, 0],
                  y: [0, -20, 0],
                }}
                transition={{
                  duration: 4,
                  delay: i * 0.5,
                  repeat: Infinity,
                  repeatType: "reverse",
                }}
                style={{
                  left: `${20 + i * 30}%`,
                  top: `${-50 - i * 20}px`,
                }}
              >
                <div
                  className="w-12 h-12 rounded-full bg-primary-500/10"
                  style={{
                    transform: `rotate(${i * 45}deg)`,
                  }}
                />
              </motion.div>
            ))}
          </div>
        </div>
      </div>
    </DefaultLayout>
  );
}
const features = [
  {
    title: "Запись и Распознавание",
    description:
      "Запись аудио встреч и точное распознавание речи с выделением спикеров",
    icon: "lucide:mic",
  },
  {
    title: "Умный Анализ",
    description:
      "Автоматическое формирование кратких пересказов и выделение обязательств",
    icon: "lucide:brain",
  },
  {
    title: "Удобный Доступ",
    description: "Просмотр результатов и отправка отчетов прямо из приложения",
    icon: "lucide:share",
  },
];
